package com.wms.repository;

import com.wms.model.operation.Operation;
import com.wms.model.operation.Palette;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaletteRepository extends JpaRepository<Palette,Integer> {

}
