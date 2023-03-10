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
