package persistence;

import org.json.JSONObject;

// The following class is taken from the Writable class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//Represents the Writable interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
