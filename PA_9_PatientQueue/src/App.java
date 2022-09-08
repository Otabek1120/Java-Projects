public class App {
    public static void main(String[] args) throws Exception {
        PatientQueue x = new PatientQueue();
        Patient patient1 = new Patient("Anat", 4);
        // x.enqueue("Ben", 1);
        // x.enqueue("nat", 1);

        // x.enqueue("Rein", 2);
        // x.enqueue("Sara", 7);
		// x.enqueue("Josh", 8);
		// x.enqueue("Carl", 9);
		// x.enqueue("Nora", 10);
        // x.enqueue("None", 11);
		// x.enqueue("None", 12);
        // x.enqueue("Ben", 3);
        // x.enqueue(patient1);
        // x.enqueue("Wu", 5);
        // x.enqueue("Seb", 6);

        x.enqueue("Abat", 1);
        x.enqueue("Den", 4);
        x.enqueue("Ben", 4);
        x.enqueue("Ceb", 4);
        

        System.out.println(x.toString());
        x.dequeue();
        
		
        
        
        
        
        
    
		System.out.println(x.toString());

        
    }
}
