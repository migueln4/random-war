package magia;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Simulation {

    private Map<String, Double> combatientes;
    private Map<String, Double> resultados;
    private List<String> participantes;
    private static int ronda;
    private List<String> listaCausas;

    public Simulation(List<String> participantes) {
        ronda = 1;
        this.combatientes = new HashMap<>();
        this.resultados = new HashMap<>();
        this.participantes = participantes;
        this.listaCausas = new ArrayList<>(getCausas());
        participantes.stream().forEach(str -> this.combatientes.put(str, 1.0));
        simulate();
    }

    private void simulate() {
        while (combatientes.size() > 1) {
            System.out.println("RONDA: "+ronda);
            String atacante = elegirAtacante();
            String atacado = elegirAtacado(atacante);
            System.out.println(atacante + "(" + this.combatientes.get(atacante) + " p.)" + " mata a " + atacado + "(" + this.combatientes.get(atacado) + " p.) "+getCausaMuerte());
            ajustarPuntos(atacante, atacado);
            ronda++;
        }
        System.out.println("LISTA FINAL: " + this.combatientes.size());
        for(String str : this.combatientes.keySet()) {
            System.out.println(str+"( "+this.combatientes.get(str)+"p.) ---- GANADOR");
            imprimirMasPuntos(str,this.combatientes.get(str));
        }
    }

    private void imprimirMasPuntos(String ganador, Double puntos) {
        this.resultados.put(ganador,puntos);
        for(String key : this.resultados.keySet()) {
            System.out.println(key+" -> "+this.resultados.get(key)+" puntos");
        }
    }

    private void ajustarPuntos(String atacante, String atacado) {
        this.resultados.put(atacado, this.combatientes.get(atacado));
        Double atacantePuntos = this.combatientes.get(atacante);
        Double atacadoPuntos = this.combatientes.get(atacado);
        Double puntosDefinitivos = (atacantePuntos + (atacadoPuntos / 2));
        Double puntosDefRound = (double) Math.round(puntosDefinitivos * 100d)/100d;
        this.combatientes.put(atacante, puntosDefRound);
        System.out.println("PUNTOS ATACANTE ("+atacante+") = "+this.combatientes.get(atacante));
        System.out.println("PUNTOS DERROTADO ("+atacado+") = "+this.combatientes.get(atacado));
        this.combatientes.remove(atacado);
        imprimirQuienesQuedan();
    }

    private void imprimirQuienesQuedan() {
        System.out.println("QUEDAN: "+this.combatientes.size()+" participantes.");
        for(String str : this.combatientes.keySet()) {
            System.out.println(str + " con " +  this.combatientes.get(str) + " puntos");
        }
        System.out.println("=========================================================");
    }

    private String elegirAtacante() {
        List<String> posibles = new ArrayList<>();
        for (String str : this.combatientes.keySet()) {
            Double valor = this.combatientes.get(str);
            for (int i = 0; i < (valor * 100); i++) {
                posibles.add(str);
            }
        }
        int rnd = (int) (Math.random() * (posibles.size() - 1));
        return posibles.get(rnd);
    }

    private String elegirAtacado(String atacante) {
        List<String> posibles = new ArrayList<>();
        for (String str : this.combatientes.keySet()) {
            if (str.equals(atacante)) {
                continue;
            }
            posibles.add(str);
        }
        int rnd = (int) (Math.random() * (posibles.size() - 1));
        return posibles.get(rnd);
    }

    private String getCausaMuerte() {
        int rnd = (int) (Math.random() * this.listaCausas.size());
        String causa = this.listaCausas.get(rnd);
        this.listaCausas.remove(rnd);
        return causa;
    }

    private List<String> getCausas() {
        List<String> lista = new ArrayList<>();
        lista.add("con la navaja de la vendimia.");
        lista.add("lanzando una Onda Vital.");
        lista.add("porque tenía la carta del Hombre Lobo y es su naturaleza.");
        lista.add("con más veneno que Mitrídates.");
        lista.add("usando el ataque Impactrueno.");
        lista.add("con un estacazo.");
        lista.add("con nocturnidad y alevosía.");
        lista.add("con una cepa chinorris del coronavirus.");
        lista.add("¡por el poder de Grey Skull!");
        lista.add("con una patada voladora.");
        lista.add("por alguna clase de venganza.");
        lista.add("con un rayaco.");
        lista.add("de aburrimiento.");
        lista.add("usando su Cuaderno de la Muerte.");
        lista.add("simplemente, usando el comando Atacar del mando.");
        lista.add("siguiendo los consejos del Doctor Muerte.");
        lista.add("con kryptonita en mal estado.");
        lista.add("extirpando su hígado y comiéndoselo con patatas fritas.");
        return lista;
    }

}