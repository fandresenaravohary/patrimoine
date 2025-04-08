package school.hei.patrimoine.cas.example;

import static java.time.Month.APRIL;
import static java.time.Month.DECEMBER;
import static java.time.Month.MAY;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;
import school.hei.patrimoine.modele.possession.TransfertArgent;
import school.hei.patrimoine.modele.possession.Correction;

/**
 * Implémentation du patrimoine de Bako pour l'année 2025.
 */
public class PatrimoineBakoSupplier extends Cas {

    // Date de démarrage de la simulation et date finale
    private static final LocalDate DATE_DEBUT = LocalDate.of(2025, APRIL, 8);
    private static final LocalDate DATE_FIN = LocalDate.of(2025, DECEMBER, 31);

    // Déclaration des comptes et de l'actif matériel
    private final Compte compteBNI;
    private final Compte compteBMOI;
    private final Compte coffreFort;
    private final Materiel ordinateur;

    public PatrimoineBakoSupplier() {
        super(DATE_DEBUT, DATE_FIN, new Personne("Bako"));
        compteBNI = new Compte("Compte BNI", DATE_DEBUT, ariary(2_000_000));
        compteBMOI = new Compte("Compte BMOI", DATE_DEBUT, ariary(625_000));
        coffreFort = new Compte("Coffre-fort", DATE_DEBUT, ariary(1_750_000));
        ordinateur = new Materiel("Ordinateur portable", DATE_DEBUT, DATE_FIN, ariary(3_000_000), -0.12);
    }

    @Override
    protected String nom() {
        return "Patrimoine de Bako (2025)";
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected void init() {
        // Les soldes initiaux sont déjà fixés lors de la création des comptes et du matériel.
    }

    @Override
    public Set<Possession> possessions() {
        LocalDate premierSalaire = LocalDate.of(2025, MAY, 2);
        new FluxArgent(
                "Salaire BNI",
                compteBNI,
                premierSalaire,
                DATE_FIN,
                2, // jour du mois = 2
                ariary(2_125_000));

        LocalDate premierVirement = LocalDate.of(2025, MAY, 3);
        new TransfertArgent(
                "Virement vers épargne",
                compteBNI,
                compteBMOI,
                premierVirement,
                DATE_FIN,
                3, // jour du mois = 3
                ariary(-200_000)
        );

        new FluxArgent(
                "Loyer colocation",
                compteBNI,
                DATE_DEBUT,
                DATE_FIN,
                26,
                ariary(-600_000));

        LocalDate premiereDepense = LocalDate.of(2025, MAY, 1);
        new FluxArgent(
                "Dépenses de vie",
                compteBNI,
                premiereDepense,
                DATE_FIN,
                1,
                ariary(-700_000));

        return Set.of(compteBNI, compteBMOI, coffreFort, ordinateur);
    }

    @Override
    protected void suivi() {
        new Correction(new FluxArgent("Correction ponctuelle", compteBNI, ajd, ariary(50_000)));
    }
}
