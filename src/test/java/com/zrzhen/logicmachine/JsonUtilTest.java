package com.zrzhen.logicmachine;

import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.util.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonUtilTest {


    @Test
    public void fact() {

        Fact fact = new Fact(1);
        fact.setAtomicId(2);
        fact.setConnective("AND");

        Fact fact2 = new Fact(23);
        fact2.setAtomicId(2);
        fact2.setConnective("AND");

        List<Fact> facts = new ArrayList<>();
        facts.add(fact2);
        fact.setChildFacts(facts);

        String json = JsonUtil.entity2Json(fact);
        System.out.println(json);

        Fact fact1 = JsonUtil.str2Entity(json, Fact.class);

        System.out.println(fact1.toString());
    }

}