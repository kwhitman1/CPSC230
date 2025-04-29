import java.util.*;
public class Equipment {
	private final Map<String, Integer> equipmentAvailable;
    private final Map<String, Object> locks;

    public Equipment(Map<String, Integer> equipmentCounts) {
        equipmentAvailable = new HashMap<>(equipmentCounts);
        locks = new HashMap<>();
        for (String name : equipmentCounts.keySet()) {
            locks.put(name, new Object());
        }
    }

    public void getEquipment(List<String> needed) throws InterruptedException {
        for (String name : needed) {
            synchronized (locks.get(name)) {
                while (equipmentAvailable.get(name) == 0) {
                    locks.get(name).wait();
                }
                equipmentAvailable.put(name, equipmentAvailable.get(name) - 1);
            }
        }
    }

    public void loseEquipment(List<String> released) {
        for (String name : released) {
            synchronized (locks.get(name)) {
                equipmentAvailable.put(name, equipmentAvailable.get(name) + 1);
                locks.get(name).notifyAll();
            }
        }
    }

}
