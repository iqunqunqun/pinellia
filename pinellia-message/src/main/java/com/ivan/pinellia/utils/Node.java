package com.ivan.pinellia.utils;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author ivan
 * @className Node
 * @since 2020/11/25 21:36
 */

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

public class Node {

    private String name;

//    private String code;

    private List<Node> children;

    public Node(String name) {
        this.name = name;
    }

    public List<Node> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }

}
