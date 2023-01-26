import { Typography } from "@mui/material";
import React, { PureComponent } from "react";
import {
    ComposedChart,
    Line,
    Area,
    Bar,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    Scatter,
    ResponsiveContainer,
    BarChart,
    Cell,
} from "recharts";
import { getSpeedAnalysis } from "../../services/speed";
import { useTheme } from "@mui/material/styles";

const speedData = getSpeedAnalysis().map((item) => ({
    name: item.name,
    speed: item.speed,
}));

type Props = {
    width?: number;
    height?: number;
};

const SpeedChart: React.FC = ({ width = 700, height = 400 }: Props) => {
    const theme = useTheme();

    const colors: string[] = Array.from({ length: speedData.length }).map(
        (_, i) =>
            i % 2 === 0
                ? theme.palette.success.main
                : theme.palette.primary.main
    );

    return (
        <>
            <Typography variant="body1" fontWeight={"bold"}>
                Analyse de Vitesse
            </Typography>
            <BarChart width={width} height={height} data={speedData}>
                <CartesianGrid stroke="#f5f5f5" strokeDasharray={"3 3"} />
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
                <Bar
                    dataKey="speed"
                    barSize={50}
                    fill={theme.palette.primary.main}
                >
                    {speedData.map((entry, index) => (
                        <Cell
                            key={`cell-${index}`}
                            fill={colors[index]}
                            path={"10"}
                        />
                    ))}
                </Bar>
            </BarChart>
        </>
    );
};

export default SpeedChart;
