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
import { EntryExit } from "../../services/entryExit";
import Typography from "@mui/material/Typography";

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
    data?: EntryExit[];
};

const RadarCharts = ({ width, height, data }: Props) => {
    const theme = useTheme();
    return (
        <>
            <Typography variant="body2" fontWeight="bold">
                Entrée / Sortie par type
            </Typography>
            <RadarChart
                width={width}
                height={height}
                cx="50%"
                cy="50%"
                outerRadius="80%"
                data={data}
            >
                <PolarGrid />
                <PolarAngleAxis dataKey="type" />
                <PolarRadiusAxis />
                <Radar
                    dataKey="entry"
                    stroke={theme.palette.primary.main}
                    fill={theme.palette.primary.main}
                    fillOpacity={0.6}
                />
                <Radar
                    dataKey="exit"
                    stroke={theme.palette.warning.main}
                    fill={theme.palette.warning.main}
                    fillOpacity={0.6}
                />
            </RadarChart>
        </>
    );
};

export default RadarCharts;
