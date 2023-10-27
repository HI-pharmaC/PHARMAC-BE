package PharmaC.backend.domain.Medicine.domain;

import PharmaC.backend.domain.Medicine.dto.MedicineDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 20)
    @Column(name = "item_code")
    private String itemCode;

    @NotNull
    @Size(max = 50)
    private String company;

    @Nullable
    private String image;

    @NotNull
    private String effect;

    @Nullable
    @Column(name = "take_method")
    private String takeMethod;

    @NotNull
    private String precaution;

    @NotNull
    private String caution;

    @NotNull
    private String interaction;

    @NotNull
    @Column(name = "side_effect")
    private String sideEffect;

    @NotNull
    private String storage;

    @Builder
    private Medicine(Long id, String name, String itemCode, String company, String image, String effect, String takeMethod, String precaution, String caution, String interaction, String sideEffect, String storage) {
        this.id = id;
        this.name = name;
        this.itemCode = itemCode;
        this.company = company;
        this.image = image;
        this.effect = effect;
        this.takeMethod = takeMethod;
        this.precaution = precaution;
        this.caution = caution;
        this.interaction = interaction;
        this.sideEffect = sideEffect;
        this.storage = storage;
    }

    // 정적 메소드 팩토리
    public static Medicine of(MedicineDTO medicineDTO) {
        return Medicine.builder()
                .name(medicineDTO.getName())
                .itemCode(medicineDTO.getItemCode())
                .company(medicineDTO.getCompany())
                .image(medicineDTO.getImage())
                .effect(medicineDTO.getEffect())
                .takeMethod(medicineDTO.getTakeMethod())
                .precaution(medicineDTO.getPrecaution())
                .caution(medicineDTO.getCaution())
                .interaction(medicineDTO.getInteraction())
                .sideEffect(medicineDTO.getSideEffect())
                .storage(medicineDTO.getStorage())
                .build();
    }
}
