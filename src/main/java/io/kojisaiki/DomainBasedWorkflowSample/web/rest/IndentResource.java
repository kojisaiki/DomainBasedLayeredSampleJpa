package io.kojisaiki.DomainBasedWorkflowSample.web.rest;

import io.kojisaiki.DomainBasedWorkflowSample.entity.Indent;
import io.kojisaiki.DomainBasedWorkflowSample.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * 発注書リソース
 * TODO:1アプリが1サブシステムになる場合、発注書１つがコントローラになるか？要検討
 */
@RestController
@RequestMapping("/_api")
public class IndentResource {

    IndentService indentService;

    public IndentResource(
            IndentService indentService
    ) {
        this.indentService = indentService;
    }

    @GetMapping("/indents")
    public ResponseEntity<List<Indent>> getIndents() {
        return ResponseEntity.ok().body(indentService.getIndents());
    }

    @PostMapping("/indents")
    public ResponseEntity<Indent> postIndent(@RequestBody Indent indent) throws URISyntaxException {
        Indent allowdIndent = indentService.addNewIndent(indent);
        return ResponseEntity.created(
                new URI("/indents/" + String.valueOf(allowdIndent.getId()))
        ).body(allowdIndent);
    }
}
