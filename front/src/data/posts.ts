const data = [
    {
        title: "Poste 2",
        location: {
            latitude: 44.795955,
            longitude: -0.606845,
        },
        type: "radar",
        equipment: "radar TagMaster",
        vehicle_discriminations: {
            "2RM/VL": 2.6,
            "VL/PL": 8,
            Bus: ">10m",
        },
        period: "From Tuesday 4th to Monday 17th October",
        findings: {
            mainly_buses: true,
            few_PL_observed: true,
            more_traffic_in_entry: true,
            exit_direction_restricted_except_for_buses_public_services_ambulances_residents_and_two_wheeled_vehicles:
                true,
        },
    },
    {
        title: "Poste 3",
        location: {
            latitude: 44.798725,
            longitude: -0.60484,
        },
        type: "radar",
        equipment: "radar TagMaster",
        vehicle_discriminations: {
            "2RM/VL": 2.6,
            "VL/PL": 8,
        },
        period: "From Tuesday 11th to Sunday 16th October",
        findings: {
            strong_presence_of_large_utility_vehicles: true,
            few_buses_on_this_axis: true,
            more_traffic_in_exit: true,
            impossibility_to_dissociate_buses_and_PL: true,
        },
    },
    {
        title: "Poste 4",
        location: {
            latitude: 44.80193,
            longitude: -0.59816,
        },
        type: "radar",
        equipment: "double radar Viking",
        vehicle_discriminations: {
            SER_entry: {
                "Velo/Moto": 220,
                "Moto/VL": 400,
                "VL/PL": 900,
            },
            SER_exit: {
                "Moto/VL": 190,
                "VL/PL": 1160,
            },
        },
        period: "From Tuesday 4th to Monday 17th October (no counts in entry on Monday 10th October between 10am and 3:40pm)",
        findings: {
            strong_presence_of_buses: true,
            low_PL_traffic: true,
            impossibility_to_dissociate_buses_and_PL: true,
            important_point:
                "Exit from the faculty generates significant delays, particularly in the late afternoon, during the high school's exit hours. Due to a much too low speed, the radar severely underestimates the traffic. As an example, on Tuesday 11th October between 4pm and 6pm, the Cerema manually counted 1,338 vehicles, while the radar only",
        },
    },
    {
        title: "Poste 5",
        location: {
            latitude: 44.8051,
            longitude: -0.601285,
        },
        type: "radar",
        equipment: "single radar Viking",
        vehicle_discriminations: {
            SER: {
                "Velo/Moto": 250,
                "Moto/VL": 360,
                "VL/PL": 1140,
            },
        },
        period: "From Tuesday 4th to Monday 17th October",
        findings: {
            few_buses_and_PL: true,
            more_traffic_in_exit: true,
        },
    },
    {
        title: "Poste 9",
        location: {
            latitude: 44.808745,
            longitude: -0.596255,
        },
        type: "tube",
        equipment: "tube Mixtra",
        vehicle_discriminations: {
            "VL/PL": 4.5,
        },
        period: "From Tuesday 4th to Monday 17th October",
        findings: {
            few_PL_and_buses: true,
            more_traffic_in_entry: true,
        },
    },
    {
        title: "Poste 15",
        location: {
            latitude: 44.8049,
            longitude: -0.605485,
        },
        type: "tube",
        equipment: "tube cycliste Delta",
        period: "From Tuesday 4th to Monday 17th October",
        findings: {
            mainly_bikes: true,
            some_scooters: true,
            cargo_bikes: true,
            rollers: true,
            overboards: true,
            more_traffic_toward_RU1: true,
        },
    },
    {
        title: "Poste 17 sens 1",
        location: {
            latitude: 44.8031,
            longitude: -0.61018,
        },
        type: "radar",
        equipment: "double radar Viking",
        vehicle_discriminations: {
            SER_toward_avenue_Schweitzer: {
                "Moto/VL": 170,
                "VL/PL": 880,
            },
            SER_toward_Poste_16: {
                "Moto/VL": 200,
                "VL/PL": 790,
            },
        },
        period: "From Tuesday 4th to Monday 17th October",
        findings: {
            bikes_on_bike_lanes_not_captured_by_radars: true,
            few_buses_on_this_axis: true,
            high_PL_traffic: true,
            high_traffic_on_this_axis: 14000,
            more_traffic_toward_avenue_Schweitzer: true,
        },
    },
    {
        title: "Poste 17 sens 2",
        location: {
            latitude: 44.802955,
            longitude: -0.61032,
        },
        type: "radar",
        equipment: "double radar Viking",
        vehicle_discriminations: {
            SER_toward_avenue_Schweitzer: {
                "Moto/VL": 170,
                "VL/PL": 880,
            },
            SER_toward_Poste_16: {
                "Moto/VL": 200,
                "VL/PL": 790,
            },
        },
        period: "From Tuesday 4th to Monday 17th October",
        findings: {
            bikes_on_bike_lanes_not_captured_by_radars: true,
            few_buses_on_this_axis: true,
            high_PL_traffic: true,
            high_traffic_on_this_axis: 14000,
            more_traffic_toward_avenue_Schweitzer: true,
        },
    },
    {
        title: "Poste 19",
        location: {
            latitude: 44.79277,
            longitude: -0.61642,
        },
        type: "tube",
        equipment: "tube Mixtra",
        vehicle_discriminations: {
            "VL/PL": 4.5,
        },
        period: "From Wednesday 5th to Tuesday 18th October",
    },
];

export default data;
