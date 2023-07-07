package site.penghao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * 单独给 SearchPojo 创建一个实体类是为了与 pojo 模块中的定义区分，
 * 避免 pojo 中的对象被过多修饰
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPojo implements Serializable {

    /**
     * 序列化 ID，使用 RabbitMQ 上传的对象必须实现序列化，并且具有序列化 ID
     */
    public static final long serialVersionUID = 1L;

    @Field
    private Integer id;

    @Field
    private String name;

    @Field
    private Double price;
}
