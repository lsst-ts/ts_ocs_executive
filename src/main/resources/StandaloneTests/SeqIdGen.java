
// 
// Compile as follows:
//     $ javac SeqIdGen.java SeqIdGenClient.java
// 
// Run as follows:
//     $ java SeqIdGenClient

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <h2>Sequence Id Generator</h2>
 * <p>
 * The non-instantiable {@code SequenceIdGenerator} utility/helper class
 * that implements a {@link Thread} safe version of a monotonic counter.
 * <p>
 * Example: 
 * <pre>
 *  {@code string seqId = SequenceIdGenerator.getInstance().getNext(); } 
 * </pre>
 */

public final class SeqIdGen {
    
    private static volatile SeqIdGen INSTANCE;

    private static AtomicInteger atomicSranInt;

    // private Cstr so no direct instances can be made    
    private SeqIdGen() {

    	// Prevent forming new instance via reflection API
    	if ( INSTANCE != null ) {
            
            throw new RuntimeException( "Use getInstance () method to get the singleton instance of this class" );
    	}
    }

    private static void createInstance() {
        
        SeqIdGen INSTANCE = SeqIdGen.INSTANCE;

    	// Double-check locking pattern
    	if ( INSTANCE == null ) {

            // Thread safe
            synchronized( SeqIdGen.class ) {
                
                INSTANCE = SeqIdGen.INSTANCE;

                if ( INSTANCE == null ) { 

                    SeqIdGen.INSTANCE = INSTANCE = new SeqIdGen();

                    atomicSranInt = new AtomicInteger( ThreadLocalRandom.current().nextInt(1) ); 

                    System.out.println( "initial value: " + atomicSranInt.intValue() );
                }
            }
    	}
    }

    public static SeqIdGen getInstance() {
        
        createInstance();
        
        return SeqIdGen.INSTANCE;
    }
        
    public static String getNext() {
     
        atomicSranInt.incrementAndGet();
        
        return Integer.toUnsignedString( atomicSranInt.get() );
    }


    public static String getNext( int offset ) {
        
        atomicSranInt.getAndAdd( offset );
        
        return Integer.toUnsignedString( atomicSranInt.get() );
    }

    public static String getNextBlock( ) {
        
        if ( (atomicSranInt.intValue() % 10) > 0 ) {
                
            atomicSranInt.getAndUpdate( x -> x + ( 10 - ( atomicSranInt.intValue() % 10 ) ) );

            //atomicSranInt.updateAndGet( x -> x + ( 10 - ( atomicSranInt.intValue() % 10 ) ) );
                
        	//return Integer.toUnsignedString( atomicSranInt.get() );
            
        }

        return Integer.toUnsignedString( atomicSranInt.get() );
        
        //else {

        	//return SeqIdGen.getNext();
        //}
    }

}


