package homework6;

import homework6.db.dao.CategoriesMapper;
import homework6.db.model.CategoriesExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory;
//NyBatis Configuration file
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

//    public static Integer countNumberOfAllCategories() {
//        CategoriesMapper categoriesMapper = CategoriesMapper(resource);
//        CategoriesExample example = new CategoriesExample();
//        return Math.toIntExact(categoriesMapper.countByExample(example));
//    }
}
