package io.kojisaiki.DomainBasedLayeredSampleJpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 発注書本体
 */
@Entity
@Table(name="indent_header")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Indent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String title;

    /**
     * 発注書明細
     */
    @OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name = "indent_header_id")
    private List<IndentDetail> indentDetails = new ArrayList<>();

}
