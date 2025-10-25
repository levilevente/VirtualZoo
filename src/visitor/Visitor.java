package visitor;

import animal.entities.Bird;
import animal.entities.Mammal;
import animal.entities.Reptile;

public interface Visitor {
    void visitReptile(Reptile reptile);
    void visitMammal(Mammal mammal);
    void visitBird(Bird bird);
}
