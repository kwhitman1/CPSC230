import java.util.*;

public class Doctor extends Thread {
	private final Vector<Patient> patients;
    private final Equipment equipment;

    public Doctor(Vector<Patient> patients, Equipment equipment) {
        this.patients = patients;
        this.equipment = equipment;
    }

    @Override
    public void run() {
        while (true) {
            Patient p;
            synchronized (patients) {
                if (patients.isEmpty()) break;
                p = patients.remove(0);
            }

            List<String> sorted = new ArrayList<>(p.equipment);
            Collections.sort(sorted);

            try {
                equipment.getEquipment(sorted);

                long end = System.currentTimeMillis() + p.treatmentTime;
                while (System.currentTimeMillis() < end) {
                    Thread.sleep(end - System.currentTimeMillis());
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                equipment.loseEquipment(sorted);
            }
        }
    }
}


