package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.gs00100.jpa.model.VGroupMembers;

/**
 * Interface para la clase VGrupoMembers.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-11
 * 
 */
public interface VGroupMembersRepositorio extends JpaRepository <VGroupMembers, Long>{
        
        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        public List<VGroupMembers> findAll();
        
        /**
         * Metodo para buscar un objeto por NAME.
         * 
         * @param indice
         * @return
         */
        public VGroupMembers findByName(String name);

        /**
         * Metodo para buscar un objetos por MEMBER.
         * 
         */
        public List<VGroupMembers> findByMember(String member);
}
