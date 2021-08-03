package persistence;

import org.json.JSONObject;

// Cite: This class is the same from UBC CPSC 210 JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
