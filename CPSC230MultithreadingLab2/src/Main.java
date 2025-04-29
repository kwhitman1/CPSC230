import java.util.*;
import javax.swing.JFileChooser;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select equipment file");
        int result = chooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected");
            return;
        }

        File file = chooser.getSelectedFile();
        Scanner scanner = new Scanner(file);

        int numEquipment = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> equipmentCounts = new HashMap<>();
        for (int i = 0; i < numEquipment; i++) {
            String[] parts = scanner.nextLine().split(" ");
            equipmentCounts.put(parts[0], Integer.parseInt(parts[1]));
        }

        int numPatients = Integer.parseInt(scanner.nextLine());
        List<Patient> originalPatients = new ArrayList<>();

        for (int i = 0; i < numPatients; i++) {
            int time = Integer.parseInt(scanner.nextLine().trim());
            String[] tools = scanner.nextLine().trim().split("\\s+");
            List<String> toolList = new ArrayList<>();
            for (String t : tools) toolList.add(t);
            originalPatients.add(new Patient(time, toolList));
        }
        scanner.close();

        for (int numDoctors : new int[]{1, 2, 4, 8}) {
            Vector<Patient> patientsCopy = new Vector<>();
            for (Patient p : originalPatients) {
                patientsCopy.add(new Patient(p.treatmentTime, new ArrayList<>(p.equipment)));
            }

            Equipment equipment = new Equipment(equipmentCounts);

            List<Doctor> doctors = new ArrayList<>();
            long start = System.currentTimeMillis();

            for (int i = 0; i < numDoctors; i++) {
                Doctor d = new Doctor(patientsCopy, equipment);
                doctors.add(d);
                d.start();
            }

            for (Doctor d : doctors) {
                d.join();
            }

            long end = System.currentTimeMillis();
            System.out.println("Number of Doctors: " + numDoctors + "   Time: " + (end - start) + " ms");
        }
    }
    
}