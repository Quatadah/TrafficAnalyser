import { Typography } from "@mui/material";
import { height } from "@mui/system";
import React from "react";
import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
} from "recharts";

interface ChartData {
    name: string;
    sales: number;
    profit: number;
}

const data: ChartData[] = [
    { name: "Jan", sales: 4000, profit: 2400 },
    { name: "Feb", sales: 3000, profit: 1398 },
    { name: "Mar", sales: 2000, profit: 9800 },
    { name: "Apr", sales: 2780, profit: 3908 },
    { name: "May", sales: 1890, profit: 4800 },
    { name: "Jun", sales: 2390, profit: 3800 },
    { name: "Jul", sales: 3490, profit: 4300 },
];

type Props = {
    width?: number;
    height?: number;
};

const BarChartExample: React.FC = ({ width = 700, height = 400 }: Props) => {
    return (
        <>
            <Typography variant="body1">BarChart</Typography>
            <BarChart width={width} height={height} data={data}>
                <XAxis dataKey="name" />
                <YAxis />
                <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
                <Tooltip />
                <Legend />
                <Bar dataKey="sales" fill="#8884d8" />
                <Bar dataKey="profit" fill="#82ca9d" />
            </BarChart>
        </>
    );
};

export default BarChartExample;
