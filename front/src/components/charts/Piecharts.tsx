import { Typography } from "@mui/material";
import { useTheme } from "@mui/material/styles";
import React, { PureComponent } from "react";
import { PieChart, Pie, Sector, Cell, ResponsiveContainer } from "recharts";

const data01 = [
    { name: "Group A", value: 400 },
    { name: "Group B", value: 300 },
    { name: "Group C", value: 300 },
    { name: "Group D", value: 200 },
];
const data02 = [
    { name: "A1", value: 100 },
    { name: "A2", value: 300 },
    { name: "B1", value: 100 },
    { name: "B2", value: 80 },
    { name: "B3", value: 40 },
    { name: "B4", value: 30 },
    { name: "B5", value: 50 },
    { name: "C1", value: 100 },
    { name: "C2", value: 200 },
    { name: "D1", value: 150 },
    { name: "D2", value: 50 },
];

type Props = {
    width?: number;
    height?: number;
};

const PieCharts = ({ width = 700, height = 400 }: Props) => {
    const theme = useTheme();
    return (
        <>
            <Typography variant="body1">PieChart</Typography>
            <PieChart width={width} height={height}>
                <Pie
                    data={data01}
                    dataKey="value"
                    cx="50%"
                    cy="50%"
                    outerRadius={60}
                    fill={theme.palette.primary.main}
                />
                <Pie
                    data={data02}
                    dataKey="value"
                    cx="50%"
                    cy="50%"
                    innerRadius={70}
                    outerRadius={90}
                    fill={theme.palette.secondary.main}
                    label
                />
            </PieChart>
        </>
    );
};

export default PieCharts;