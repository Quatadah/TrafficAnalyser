import { Typography } from "@mui/material";
import { useTheme } from "@mui/material/styles";
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
import { traffic } from "./Linecharts";

export interface trafficDate {
    direction: string;
    values: {
        date: string;
        numberPL: number;
        numberVL: number;
        number2R: number;
    }[];
}

type Props = {
    width?: number;
    height?: number;
    trafficData?: trafficDate;
};

const BarChartExample: React.FC = ({
    width = 700,
    height = 400,
    trafficData,
}: Props) => {
    const theme = useTheme();
    return (
        <>
            <Typography variant="body1" fontWeight={"bold"}>
                Traffic {trafficData?.direction}
            </Typography>
            {trafficData && (
                <BarChart
                    width={width}
                    height={height}
                    data={trafficData.values}
                    barSize={200}
                >
                    <XAxis dataKey="date" />
                    <YAxis />
                    <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
                    <Tooltip />
                    <Legend />
                    <Bar dataKey="numberPL" fill={theme.palette.warning.main} />
                    <Bar dataKey="numberVL" fill={theme.palette.primary.main} />
                    <Bar dataKey="number2R" fill={theme.palette.success.main} />
                </BarChart>
            )}
        </>
    );
};

export default BarChartExample;
