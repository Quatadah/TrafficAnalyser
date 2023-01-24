import React from "react";
import { useTheme } from "@mui/material/styles";
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    ResponsiveContainer,
} from "recharts";
import { Box } from "@mui/system";
import { Typography } from "@mui/material";

interface ChartData {
    name: string;
    uv: number;
    pv: number;
    amt: number;
}

const data: ChartData[] = [
    { name: "Page A", uv: 4000, pv: 2400, amt: 2400 },
    { name: "Page B", uv: 3000, pv: 1398, amt: 2210 },
    { name: "Page C", uv: 2000, pv: 9800, amt: 2290 },
    { name: "Page D", uv: 2780, pv: 3908, amt: 2000 },
    { name: "Page E", uv: 1890, pv: 4800, amt: 2181 },
    { name: "Page F", uv: 2390, pv: 3800, amt: 2500 },
    { name: "Page G", uv: 3490, pv: 4300, amt: 2100 },
];

type Props = {
    width?: number;
    height?: number;
};

const RechartsExample: React.FC = ({ width, height }: Props) => {
    const theme = useTheme();
    return (
        <>
            <Typography variant="body1">LineChart</Typography>
            <ResponsiveContainer width={width} height={height}>
                <LineChart data={data}>
                    <XAxis dataKey="name" />
                    <YAxis />
                    <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
                    <Tooltip />
                    <Legend />
                    <Line
                        type="monotone"
                        dataKey="uv"
                        stroke={theme.palette.primary.main}
                    />
                    <Line
                        type="monotone"
                        dataKey="pv"
                        stroke={theme.palette.secondary.main}
                    />
                </LineChart>
            </ResponsiveContainer>
        </>
    );
};

export default RechartsExample;
