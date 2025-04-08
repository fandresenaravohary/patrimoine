package school.hei.patrimoine.cas.example;

import static java.time.Month.*;
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

public class PatrimoineTiana extends Cas {

    private static final LocalDate DATE_DEBUT = LocalDate.of(2025, APRIL, 8);
    private static final LocalDate DATE_FIN = LocalDate.of(2026, MARCH, 31);

    private final Compte compteBancaire;
    private final Materiel terrain;

    public PatrimoineTiana() {
        super(DATE_DEBUT, DATE_FIN, new Personne("Tiana"));
        compteBancaire = new Compte("Compte bancaire", DATE_DEBUT, ariary(60_000_000));
        terrain = new Materiel("Terrain bâti", DATE_DEBUT, DATE_FIN, ariary(100_000_000), +0.10);
    }

    @Override
    protected String nom() {
        return "Patrimoine de Tiana (2025-2026)";
    }

    @Override
    protected Devise devise() {
        return MGA;
    }

    @Override
    protected void init() {
        // Les soldes initiaux sont déjà affectés lors de la création du compte et du terrain.
    }

    @Override
    public Set<Possession> possessions() {
        // --- Dépenses mensuelles pour loger et nourrir la famille
        // Le premier prélèvement a lieu le 1er mai 2025, puis chaque 1er du mois jusqu'au 31 mars 2026.
        new FluxArgent(
                "Dépenses familiales",
                compteBancaire,
                LocalDate.of(2025, MAY, 1),
                DATE_FIN,
                1,
                ariary(-4_000_000)
        );

        // --- Projet entrepreneurial : dépenses de 5 000 000 Ar mensuels
        // Du 1er juin 2025 au 31 décembre 2025, prélevés le 5 de chaque mois.
        new FluxArgent(
                "Dépenses projet entrepreneurial",
                compteBancaire,
                LocalDate.of(2025, JUNE, 5),
                LocalDate.of(2025, DECEMBER, 5),
                5,
                ariary(-5_000_000)
        );

        new FluxArgent(
                "Revenu projet (10%)",
                compteBancaire,
                LocalDate.of(2025, MAY, 1),
                ariary(7_000_000)
        );

        new FluxArgent(
                "Revenu projet (90%)",
                compteBancaire,
                LocalDate.of(2026, JANUARY, 31),
                ariary(63_000_000)
        );

        new FluxArgent(
                "Prêt bancaire reçu",
                compteBancaire,
                LocalDate.of(2025, JULY, 27),
                ariary(20_000_000)
        );

        new FluxArgent(
                "Remboursement prêt bancaire",
                compteBancaire,
                LocalDate.of(2025, AUGUST, 27),
                LocalDate.of(2026, MARCH, 27),
                27,
                ariary(-2_000_000)
        );

        return Set.of(compteBancaire, terrain);
    }

    @Override
    protected void suivi() {
        // Aucun ajustement ponctuel spécifique pour ce cas de Tiana.
    }
}
