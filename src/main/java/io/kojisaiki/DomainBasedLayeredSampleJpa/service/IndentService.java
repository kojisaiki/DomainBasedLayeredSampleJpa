package io.kojisaiki.DomainBasedLayeredSampleJpa.service;

import io.kojisaiki.DomainBasedLayeredSampleJpa.entity.Indent;
import io.kojisaiki.DomainBasedLayeredSampleJpa.entity.repository.IndentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 発注書サービス
 * TODO:1アプリが1サブシステムになる場合、発注書が1サービスになるか？要検討
 */
@Service
public class IndentService {

    @Autowired
    IndentRepository indentRepository;

    /**
     * 発注書全件取得
     * @return
     */
    public List<Indent> getIndents() {
        return indentRepository.findAll();
    }

    /**
     * 発注書1件登録
     * @param indent
     * @return
     */
    public Indent addNewIndent(Indent indent) {
        Indent allowdEntity = this.indentRepository.save(indent);

        return allowdEntity;
    }
}
