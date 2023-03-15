package com.qapaq.lg00100.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.lg00100.jpa.model.VGroupMembers;
import com.qapaq.lg00100.jpa.queries.VGroupMembersRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Objeto para dar soporte a servicio REST de V grupo members
 */
@Service
@Transactional
@RequiredArgsConstructor
public class VGroupMembersServicio {

    @Autowired
    private final VGroupMembersRepositorio vGroupMembersRepositorio;

    /**
     * Metodo para buscar lista de V grupo members por nombre.
     * 
     */
    public List<VGroupMembers> findByNombreVGroupMembers(String member) {
        return vGroupMembersRepositorio.findByMember(member);
    }
}