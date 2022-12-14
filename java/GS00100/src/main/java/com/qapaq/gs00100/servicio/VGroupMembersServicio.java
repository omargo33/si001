package com.qapaq.gs00100.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qapaq.gs00100.jpa.queries.VGroupMembersRepositorio;
import com.qapaq.gs00100.jpa.model.VGroupMembers;


/**
 *  Objeto para dar soporte a servicio REST de V grupo members
 */
@Service
@Transactional ("gs001001TransactionManager")
public class VGroupMembersServicio {
    
        private final VGroupMembersRepositorio vGroupMembersRepositorio;
        /**
        * Metodo para crear los repositorios.
        * @param vGroupMembersRepositorio
        *
        */
        @Autowired
        public VGroupMembersServicio(VGroupMembersRepositorio vGroupMembersRepositorio) {
            this.vGroupMembersRepositorio = vGroupMembersRepositorio;
        }
        
        /**
        * Metodo para buscar lista de V grupo members por nombre.
        * 
        */
        public List<VGroupMembers> findByNombreVGroupMembers(String member){
            return vGroupMembersRepositorio.findByMember(member);
        }
}
