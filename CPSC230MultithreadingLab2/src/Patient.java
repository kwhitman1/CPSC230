import java.util.List;

public class Patient {
	int treatmentTime;
    List<String> equipment;

    public Patient(int treatmentTime, List<String> equipment) {
        this.treatmentTime = treatmentTime;
        this.equipment = equipment;
    }

}
