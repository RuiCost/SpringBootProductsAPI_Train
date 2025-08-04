package com.example.produtoisapi.productManagement.api;

import com.example.produtoisapi.productManagement.model.Category;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-04T10:24:47+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class CategoryViewMapperImpl extends CategoryViewMapper {

    @Override
    public CategoryView toCategoryView(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryView categoryView = new CategoryView();

        categoryView.setIdCategory( category.getIdCategory() );
        categoryView.setName( category.getName() );

        return categoryView;
    }

    @Override
    public Iterable<CategoryView> toCategoryView(Iterable<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        ArrayList<CategoryView> iterable = new ArrayList<CategoryView>();
        for ( Category category : categories ) {
            iterable.add( toCategoryView( category ) );
        }

        return iterable;
    }
}
