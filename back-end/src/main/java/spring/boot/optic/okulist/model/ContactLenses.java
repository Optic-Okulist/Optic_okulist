package spring.boot.optic.okulist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@SQLDelete(sql = "Update contact_lenses SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "contact_lenses")
public class ContactLenses extends Product {
    private double diopter;
    private double cylinder;
    private double angle;
    private double baseCurve;
    private String lensColor;
    private String adidation;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
