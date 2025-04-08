package school.hei.patrimoine.cas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.patrimoine.modele.Argent.ariary;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import school.hei.patrimoine.cas.example.PatrimoineTiana;
import school.hei.patrimoine.modele.Patrimoine;

class PatrimoineDeTianaTest {

    private final PatrimoineTiana casDeTiana = new PatrimoineTiana();

    private Patrimoine patrimoineDeTiana() {
        return casDeTiana.patrimoine();
    }

    @Test
    void tiana_a_un_patrimoine_positif_au_31_mars_2026() {
        var patrimoine = patrimoineDeTiana();
        var au31Mars2026 = patrimoine.projectionFuture(LocalDate.of(2026, 3, 31));

        assertEquals(ariary(155_000_000), au31Mars2026.getValeurComptable());
    }
}
