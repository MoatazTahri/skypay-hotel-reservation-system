package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enumerations.RoomType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
    int number;
    RoomType type;
    int pricePerNight;
}
