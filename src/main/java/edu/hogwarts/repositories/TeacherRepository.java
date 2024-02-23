package edu.hogwarts.repositories;

import edu.hogwarts.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

//Jpa Repository er et abstractions niveau som gør at jeg ikke selv skal skrive min sql queries. Derfor extender vi dette interface
public interface TeacherRepository extends JpaRepository<Teacher, Integer> { //jpaRepository tager typen vi har i vores DB tabel (Teacher) og typen af den primære nøgle (Integer)

}
