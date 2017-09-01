package io.kojisaiki.DomainBasedWorkflowSample.service;

import io.kojisaiki.DomainBasedWorkflowSample.DomainBasedLayeredSampleJpaApplication;
import io.kojisaiki.DomainBasedWorkflowSample.entity.Indent;
import io.kojisaiki.DomainBasedWorkflowSample.entity.IndentDetail;
import io.kojisaiki.DomainBasedWorkflowSample.repository.IndentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DomainBasedLayeredSampleJpaApplication.class)
@Transactional
public class IndentServiceIntTest {

    @Autowired
    IndentService indentService;

    @Autowired
    IndentRepository indentRepository;

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
    public void assertThatGetIndents() {
        // given
        indents.stream()
                .forEach(indent -> {
                    indentRepository.save(indent);
                });

        // when
        List<Indent> fetched = indentService.getIndents();

        // then
        assertThat(fetched.size()).isEqualTo(2);
        assertThat(fetched.get(0).getTitle()).isEqualTo(DATA_INDENT_1_TITLE);
        assertThat(fetched.get(0).getIndentDetails().get(0).getTitle()).isEqualTo(DATA_INDENT_1_DETAIL_1_TITLE);
        assertThat(fetched.get(0).getIndentDetails().get(1).getTitle()).isEqualTo(DATA_INDENT_1_DETAIL_2_TITLE);
        assertThat(fetched.get(1).getTitle()).isEqualTo(DATA_INDENT_2_TITLE);
        assertThat(fetched.get(1).getIndentDetails().get(0).getTitle()).isEqualTo(DATA_INDENT_2_DETAIL_1_TITLE);
        assertThat(fetched.get(1).getIndentDetails().get(1).getTitle()).isEqualTo(DATA_INDENT_2_DETAIL_2_TITLE);
    }

    @Test
    public void assertThatAddNewIndent() {
        // given
        Indent allowdIndent = indentService.addNewIndent(indents.get(0));

        // when
        Indent assertIndent = indentRepository.getOne(allowdIndent.getId());

        // then
        assertThat(allowdIndent.getId()).isNotNull();
        assertThat(allowdIndent.getTitle()).isEqualTo(indents.get(0).getTitle());
        assertThat(allowdIndent.getIndentDetails().get(0).getTitle()).isEqualTo(indents.get(0).getIndentDetails().get(0).getTitle());
        assertThat(allowdIndent.getIndentDetails().get(1).getTitle()).isEqualTo(indents.get(0).getIndentDetails().get(1).getTitle());
    }
}
