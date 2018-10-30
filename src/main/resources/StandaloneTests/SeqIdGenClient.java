
// 
// Compile as follows:
//     $ javac SeqIdGen.java SeqIdGenClient.java
// 
// Run as follows:
//     $ java SeqIdGenClient
public class SeqIdGenClient {

  public static void main( String[] args ) throws Exception {

    SeqIdGen sgen = SeqIdGen.getInstance();
    //System.out.println( "\n" + "Hash: " + sgen.hashCode() + " from: " + Thread.currentThread().getName() );

    System.out.println( "\n" + sgen.getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( sgen.getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );

    System.out.println( "\n" + SeqIdGen.getInstance().getNextBlock() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );

    System.out.println( "\n" + SeqIdGen.getInstance().getNextBlock() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );

    System.out.println( "\n" + SeqIdGen.getInstance().getNextBlock() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );

    System.out.println( "\n" + SeqIdGen.getInstance().getNextBlock() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );

    System.out.println( "\n" + SeqIdGen.getInstance().getNextBlock() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );

    System.out.println( "\n" + SeqIdGen.getInstance().getNextBlock() + " from: " + Thread.currentThread().getName() );
    System.out.println( "\n" + SeqIdGen.getInstance().getNextBlock() + " from: " + Thread.currentThread().getName() );
    System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );

    //System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    //System.out.println( SeqIdGen.getInstance().getNext() + " from: " + Thread.currentThread().getName() );
    //System.out.println( SeqIdGen.getInstance().getNextBlock() + " from: " + Thread.currentThread().getName() );
    // Runnable t1 = () -> {
    // 	try {
    // 		Thread.sleep(125);
    // 	} catch( Exception e) {}
    // 	SeqIdGen sgen2 = SeqIdGen.getInstance();
    //    	System.out.println( "\n" + "Hash: " + sgen2.hashCode() + " from: " + Thread.currentThread().getName() );
    //  		System.out.println( "\n" + sgen2.getNext() + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( sgen2.getNext() + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( sgen2.getNext() + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( "\n" + sgen.getNext(0xA) + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( sgen2.getNext(10) + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( sgen2.getNext(10) + " from: " + Thread.currentThread().getName() );
    // };
    // new Thread ( t1 ).start();
    // try {
    // 	Thread.sleep(250);
    // } catch( Exception e) {}
    // SeqIdGen sgen1 = SeqIdGen.getInstance();
    //   	System.out.println( "\n" + "Hash: " + sgen1.hashCode() + " from: " + Thread.currentThread().getName() );
    // 		System.out.println( "\n" + sgen1.getNext() + " from: " + Thread.currentThread().getName() );
    // System.out.println( sgen1.getNext() + " from: " + Thread.currentThread().getName() );
    // Runnable t2 = () -> {
    // 	try {
    // 		Thread.sleep(125);
    // 	} catch( Exception e) {}
    // 	SeqIdGen sgen3 = SeqIdGen.getInstance();
    //    	System.out.println( "\n" + "Hash: " + sgen3.hashCode() + " from: " + Thread.currentThread().getName() );
    //  		System.out.println( "\n" + sgen.getNext() + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( sgen3.getNext() + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( sgen3.getNext() + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( "\n" + sgen.getNext(0xA) + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( sgen3.getNext(10) + " from: " + Thread.currentThread().getName() );
    // 	System.out.println( sgen3.getNext(10) + " from: " + Thread.currentThread().getName() );
    // };
    // new Thread ( t2 ).start();
  }
}
