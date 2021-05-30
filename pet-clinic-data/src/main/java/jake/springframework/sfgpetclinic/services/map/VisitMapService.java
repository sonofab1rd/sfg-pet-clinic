package jake.springframework.sfgpetclinic.services.map;

import jake.springframework.sfgpetclinic.model.Visit;
import jake.springframework.sfgpetclinic.services.VisitService;

import java.util.Set;

public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit visit) {
        if (visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null
                || visit.getPet().getOwner().getId() == null) {
            throw new RuntimeException("This visit is not valid!");
        }
        return super.save(visit);
    }

    @Override
    public Visit getById(Long id) {
        return super.findById(id);
    }
}