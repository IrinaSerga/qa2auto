package com.autoqa.qa2auto.entity;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ProductEntity {
    private Long id;
    private String code;
    private String name;
}



//    public ProductEntity(Long id, String code, String name) {
//        this.id = id;
//        this.code = code;
//        this.name = name;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//
//    @Override
//    public String toString() {
//        return "ProductEntity{" +
//                "id=" + id +
//                ", code='" + code + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }

