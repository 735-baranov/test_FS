package sample;


public class Cars {

    public int Id;
    public int year;

    public String model;
    public String producer;
    public String _class;

    public int getId() {
        return Id;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public String getProducer() {
        return producer;
    }

    public String get_class() {
        return _class;
    }

    public String getType_c() {
        return type_c;
    }

    public String type_c;

    public Cars(int Id, int year, String model, String producer, String _class, String type_c) {
        this.Id = Id;
        this.year = year;
        this.model = model;
        this.producer = producer;
        this._class = _class;
        this.type_c = type_c;
    }
}
