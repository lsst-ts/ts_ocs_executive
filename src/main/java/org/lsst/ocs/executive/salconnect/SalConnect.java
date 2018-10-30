/*
 * LSST Observatory Control System (OCS) Software
 * Copyright 2008-2017 AURA/LSST.
 *
 * This product includes software developed by the
 * LSST Project (http://www.lsst.org/) with contributions made at LSST partner
 * institutions.  The list of partner institutions is found at:
 * http://www.lsst.org/lsst/about/contributors .
 *
 * Use and redistribution of this software is covered by the GNU Public License
 * Version 3 (GPLv3) or later, as detailed below.  A copy of the GPLv3 is also
 * available at <http://www.gnu.org/licenses/>.
 */

package org.lsst.ocs.executive.salconnect;

import org.lsst.ocs.executive.DomainObject;
import org.lsst.ocs.executive.salservice.SalService;

import static java.lang.System.out;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 * <h2>SAL Connect</h2>
 * <p>
 * {@code SalConnect} is the Invoker class in the command pattern
 */
public class SalConnect implements DomainObject {

  @Override
  public String getName() {
    return "SalConnect";
  }

  /*
   * Command Pattern: command infc object
   */
  private SalService _salService;

  BlockingQueue<SalService> _salServiceQ;
  //Task<Void> [] _salServiceTasks;
  ObservableList<Task<Void>> _salServiceTasksList;
  int _numTasks;

  public void setSalService( SalService salService ) {

    try {
      _salServiceQ.put( salService );
    } catch ( Exception e ) {
      e.printStackTrace(
          out.printf( this.getName() + "interrupted from SalCmd.execute()" ) );
    }
  }

  public SalConnect( int numTasks ) {

    _numTasks = numTasks;

    _salServiceQ = new LinkedBlockingQueue<>();
    //_salServiceTasks = new Task[_numTasks];
    _salServiceTasksList = FXCollections.observableArrayList();
  }

  public void connect() {

    ExecutorService es = Executors.newFixedThreadPool( _numTasks );
    //ExecutorService es = Executors.newCachedThreadPool();

    int ndx;
    for ( ndx = 0; ndx < _numTasks; ndx++ ) {

      //_salServiceTasks[i] = new Task<Void>()  {
      _salServiceTasksList.add( new Task<Void>() {

        @Override
        protected Void call() {
          while ( !( _salServiceQ.isEmpty() ) ) {

            _salService = _salServiceQ.poll();

            /*
             * Command Pattern: commandIF.execute()
             */
            _salService.execute(); // indirectly calls: command.execute() [e.g. salCmd.execute()]
          }

          return null;
        }
      } );

      //es.submit( _salServiceTasks[ndx] );
      es.submit( _salServiceTasksList.get( ndx ) );
    }

    // TODO: need to loop through all tasks to check if runnung
    //while ( _salServiceTasks[_numTasks - 1].isRunning() ) { }
    _salServiceTasksList.forEach( ( Task<Void> task ) -> {

      synchronized ( new Object() ) {

        while ( task.isRunning() ) {

          try {
            Thread.sleep( 3 );
          } catch ( InterruptedException e ) {
            e.printStackTrace();
          }
        }
      }
    } );

    es.shutdown();
  }
}
