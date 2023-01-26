import React, { PureComponent } from "react";
import { useTheme } from "@mui/material/styles";
import {
    Radar,
    RadarChart,
    PolarGrid,
    PolarAngleAxis,
    PolarRadiusAxis,
    ResponsiveContainer,
} from "recharts";

const data = [
    {
        subject: "Math",
        A: 120,
        B: 110,
        fullMark: 150,
    },
    {
        subject: "Chinese",
        A: 98,
        B: 130,
        fullMark: 150,
    },
    {
        subject: "English",
        A: 86,
        B: 130,
        fullMark: 150,
    },
    {
        subject: "Geography",
        A: 99,
        B: 100,
        fullMark: 150,
    },
    {
        subject: "Physics",
        A: 85,
        B: 90,
        fullMark: 150,
    },
    {
        subject: "History",
        A: 65,
        B: 85,
        fullMark: 150,
    },
];

type Props = {
    width?: number;
    height?: number;
};

const RadarCharts = ({ width, height }: Props) => {
    const theme = useTheme();
    return (
        <RadarChart
            width={width}
            height={height}
            cx="50%"
            cy="50%"
            outerRadius="80%"
            data={data}
        >
            <PolarGrid />
            <PolarAngleAxis dataKey="subject" />
            <PolarRadiusAxis />
            <Radar
                name="Mike"
                dataKey="A"
                stroke={theme.palette.primary.main}
                fill={theme.palette.success.main}
                fillOpacity={0.6}
            />
        </RadarChart>
    );
};

export default RadarCharts;
