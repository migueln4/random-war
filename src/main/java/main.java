import file.ReadFile;
import magia.Simulation;

public class main {

    public static void main(String[] args) {
        ReadFile rf= new ReadFile();
        rf.imprimirTodo();
        Simulation s = new Simulation(rf.getParticipantesList());
    }

}
