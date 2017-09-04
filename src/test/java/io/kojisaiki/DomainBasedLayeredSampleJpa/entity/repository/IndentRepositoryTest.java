package io.kojisaiki.DomainBasedLayeredSampleJpa.entity.repository;

import io.kojisaiki.DomainBasedLayeredSampleJpa.DomainBasedLayeredSampleJpaApplication;
import io.kojisaiki.DomainBasedLayeredSampleJpa.entity.Indent;
import io.kojisaiki.DomainBasedLayeredSampleJpa.entity.IndentDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DomainBasedLayeredSampleJpaApplication.class)
@DataJpaTest
public class IndentRepositoryTest {

    @Autowired
    IndentRepository indentRepository;

    @Autowired
    TestEntityManager em;

    /**
     * test data
     */
    List<Indent> indents;

    private String DATA_INDENT_1_TITLE = "indent 1";
    private String DATA_INDENT_1_DETAIL_1_TITLE = "detail 1 - 1";
    private String DATA_INDENT_1_DETAIL_2_TITLE = "detail 1 - 2";
    private String DATA_INDENT_2_TITLE = "indent 2";
    private String DATA_INDENT_2_DETAIL_1_TITLE = "detail 2 - 1";
    private String DATA_INDENT_2_DETAIL_2_TITLE = "detail 2 - 2";

    @Before
    public void createEntity() {
        indents = new ArrayList<>();

        Indent _indent;
        List<IndentDetail> _indentDetails;
        IndentDetail _detail;

        // 1
        _indentDetails = new ArrayList<>();
        _detail = new IndentDetail();
        _detail.setTitle(DATA_INDENT_1_DETAIL_1_TITLE);
        _indentDetails.add(_detail);
        _detail = new IndentDetail();
        _detail.setTitle(DATA_INDENT_1_DETAIL_2_TITLE);
        _indentDetails.add(_detail);
        _indent = new Indent();
        _indent.setTitle(DATA_INDENT_1_TITLE);
        _indent.setIndentDetails(_indentDetails);
        indents.add(_indent);

        // 2
        _indentDetails = new ArrayList<>();
        _detail = new IndentDetail();
        _detail.setTitle(DATA_INDENT_2_DETAIL_1_TITLE);
        _indentDetails.add(_detail);
        _detail = new IndentDetail();
        _detail.setTitle(DATA_INDENT_2_DETAIL_2_TITLE);
        _indentDetails.add(_detail);
        _indent = new Indent();
        _indent.setTitle(DATA_INDENT_2_TITLE);
        _indent.setIndentDetails(_indentDetails);
        indents.add(_indent);
    }

    @Test
    public void assertThatFindAll() {
        // given
        em.persist(indents.get(0));
        em.persist(indents.get(1));
        em.flush();

        // when
        List<Indent> fetched = indentRepository.findAll();

        // then
        assertThat(fetched.size()).isEqualTo(2);
        assertThat(fetched.get(0).getTitle()).isEqualTo(DATA_INDENT_1_TITLE);
        assertThat(fetched.get(0).getIndentDetails().get(0).getTitle()).isEqualTo(DATA_INDENT_1_DETAIL_1_TITLE);
        assertThat(fetched.get(0).getIndentDetails().get(1).getTitle()).isEqualTo(DATA_INDENT_1_DETAIL_2_TITLE);
        assertThat(fetched.get(1).getTitle()).isEqualTo(DATA_INDENT_2_TITLE);
        assertThat(fetched.get(1).getIndentDetails().get(0).getTitle()).isEqualTo(DATA_INDENT_2_DETAIL_1_TITLE);
        assertThat(fetched.get(1).getIndentDetails().get(1).getTitle()).isEqualTo(DATA_INDENT_2_DETAIL_2_TITLE);
    }
}
