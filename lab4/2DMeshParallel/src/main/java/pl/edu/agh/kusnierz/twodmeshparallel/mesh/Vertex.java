package pl.edu.agh.kusnierz.twodmeshparallel.mesh;

public class Vertex {

    //label
    String mLabel;
    //links to adjacent elements
    Vertex mWest;



    Vertex mNorth;
    Vertex mEast;
    Vertex mSouth;

    //methods for adding links
    public Vertex(Vertex _west, Vertex _north, Vertex _east, Vertex _south, String _lab) {
        this.mWest = _west;
        this.mNorth = _north;
        this.mEast = _east;
        this.mSouth = _south;
        this.mLabel = _lab;
    }
    //empty constructor

    public Vertex() {
    }


    public void setWest(Vertex _west) {
        this.mWest = _west;
    }
    public void setNorth(Vertex mNorth) {
        this.mWest = mNorth;
    }
    public void setEast(Vertex _east) {
        this.mEast = _east;
    }
    public void setSouth(Vertex _south) {
        this.mSouth = _south;
    }

    public void setLabel(String _lab) {
        this.mLabel = _lab;
    }

    public Vertex getWest() {
        return this.mWest;
    }
    public Vertex getNorth() {
        return mNorth;
    }

    public Vertex getEast() {
        return this.mEast;
    }
    public Vertex getSouth() {
        return mSouth;
    }

    public String getLabel() {
        return this.mLabel;
    }
}
