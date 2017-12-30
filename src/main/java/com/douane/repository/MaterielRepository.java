package com.douane.repository;

import java.util.List;

import com.douane.entite.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MaterielRepository extends CrudRepository<Materiel, Long>{
	public Materiel findByIdMateriel(Long idmat);
	public List<Materiel> findByDetenteur(Agent detenteur);
	public List<Materiel> findByNomenMat(Nomenclature nomenclature);
	public List<Materiel> findByDirec(Direction direction);
	public List<Materiel> findByServ(Service service);
	public List<Materiel> findByBureau(Bureau bureau);
	public List<Materiel> findByValidation(boolean validation);
	public List<Materiel> findByDetenteurAndValidation(Agent detenteur, boolean validation);
	public List<Materiel> findByDetenteurAndDirec(Agent detenteur, Direction direction);
	public Long countByTypematerieladdAndDirec(TypeMateriel typemat, Direction direction);
	/*@Query("select m from Materiel m where m.direc == %?1 and m.validation == TRUE "
			+ "group by m.typematerieladd.nomenclaureParent.designation	"
			+ "order by m.typematerieladd.designation asc")
	public List<Materiel> findByDirectionAndValidationGpByNomOrdByTypemat(Direction d);*/
}
