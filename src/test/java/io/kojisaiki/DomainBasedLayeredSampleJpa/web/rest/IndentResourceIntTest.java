package io.kojisaiki.DomainBasedLayeredSampleJpa.web.rest;

import io.kojisaiki.DomainBasedLayeredSampleJpa.DomainBasedLayeredSampleJpaApplication;
import io.kojisaiki.DomainBasedLayeredSampleJpa.entity.Indent;
import io.kojisaiki.DomainBasedLayeredSampleJpa.entity.IndentDetail;
import io.kojisaiki.DomainBasedLayeredSampleJpa.repository.IndentRepository;
import io.kojisaiki.DomainBasedLayeredSampleJpa.service.IndentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DomainBasedLayeredSampleJpaApplication.class)
public class IndentResourceIntTest {

    @Autowired
    EntityManager em;

    @Autowired
    IndentService indentService;

    @Autowired
    IndentRepository indentRepository;

    /**
     * test data
     */
    List<Indent> indents;

    private MockMvc restIndentMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        IndentResource indentResource = new IndentResource(indentService);
        this.restIndentMockMvc = MockMvcBuilders.standaloneSetup(indentResource)
                .build();
    }

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
    public void getIndents() throws Exception {
        // given
        indents.stream()
                .forEach(indent -> {
                    indentRepository.save(indent);
                });

        // when, then
        restIndentMockMvc.perform(get("/_api/indents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is(DATA_INDENT_1_TITLE)))
                .andExpect(jsonPath("$[0].indentDetails[0].title", is(DATA_INDENT_1_DETAIL_1_TITLE)))
                .andExpect(jsonPath("$[0].indentDetails[1].title", is(DATA_INDENT_1_DETAIL_2_TITLE)))
                .andExpect(jsonPath("$[1].title", is(DATA_INDENT_2_TITLE)))
                .andExpect(jsonPath("$[1].indentDetails[0].title", is(DATA_INDENT_2_DETAIL_1_TITLE)))
                .andExpect(jsonPath("$[1].indentDetails[1].title", is(DATA_INDENT_2_DETAIL_2_TITLE)));
    }
}
