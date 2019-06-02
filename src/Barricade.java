public class Barricade {
    // ATTRIBUTES
    int type;           // Type of the Barricade, array of possibilities
                        // should be in the Grid class
    // int hp;
    // int mesh_id;

    // CONSTRUCTORS
    /**
     * Basic Barricade information, set to defaults
     * @param type      Type of the Barricade (with constants)
     * @param hp        Amount of health points this barricade still hsa
     * @param mesh_id
     */
    public Barricade(int type/*, int hp, int mesh_id*/) {
        this.type = type;
        // this.hp = hp;
        // this.mesh_id = mesh_id;
    }
}