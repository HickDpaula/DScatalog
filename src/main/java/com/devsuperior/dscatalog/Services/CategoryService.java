package com.devsuperior.dscatalog.Services;

import com.devsuperior.dscatalog.Entities.Category;
import com.devsuperior.dscatalog.Repositories.CategoryRepository;
import com.devsuperior.dscatalog.Services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.Services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.dto.CategoryDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
        Page<Category> categoryList = categoryRepository.findAll(pageRequest);
        return categoryList.map(x -> new CategoryDTO(x));
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj = categoryRepository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        categoryRepository.save(category);
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(CategoryDTO dto, Long id){
        try{
            Category category = categoryRepository.getOne(id);
            category.setName(dto.getName());
            categoryRepository.save(category);
            return new CategoryDTO(category);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);

        }
    }

    public void delete(Long id) {
        try {

            categoryRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(("Id not found"));
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }

    }
}
