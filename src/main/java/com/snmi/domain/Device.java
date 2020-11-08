package com.snmi.domain;

import com.snmi.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "device")
public class Device extends BaseEntity {

    @NotNull(message = "Vendor must not be null")
    @Size(min = 3, max = 50, message = "Vendor length must be between 3 and 50")
    @Column(name = "vendor")
    private String vendor;

    @NotNull(message = "Date created must not be null")
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @NotNull(message = "Status must not be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gateway_id")
    private Gateway gateway;

    public void updateDevice(
            @NotNull @Size(min = 3, max = 50) String vendor,
            @NotNull LocalDate dateCreated,
            @NotNull Status status
    ) {
        this.vendor = vendor;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Device device = (Device) o;
        return super.getId().equals(device.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), vendor, dateCreated, status, gateway);
    }
}
