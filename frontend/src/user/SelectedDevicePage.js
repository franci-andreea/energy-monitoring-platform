import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  ChartData,
} from "chart.js";
import { Chart, Line } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

const SelectedDevicePage = () =>
{
    let navigate = useNavigate();

    const location = useLocation();
    const state = location.state;

    const [deviceConsumptions, setDeviceConsumptions] = useState([]);
    const [unfilteredDeviceConsumptions, setUnfilteredDeviceConsumptions] = useState([]);
    const [refreshKey, setRefreshKey] = useState(0);

    const [selectedDate, setSelectedDate] = useState([]);

    const chartData = {
        labels: deviceConsumptions.map((e) => e.date), //todo change
        datasets: [
          {
            label: "Consumption Per Hour",
            data: deviceConsumptions.map((e) => e.energyConsumption), //todo change
            tension: 0.1,
          },
        ],
      };

    var filteredDeviceConsumptions = [];

    console.log(deviceConsumptions);

    useEffect(
        () => {
            if(checkUser())
            {
                getDeviceConsumptions().then((val) => {setDeviceConsumptions(val);});
                getDeviceConsumptions().then((val) => {setUnfilteredDeviceConsumptions(val);});
            }
        }, [refreshKey]
    )

    useEffect(
        () => {
            filterEnergyConsumptionByDate().then((val) => {setDeviceConsumptions(val)});
        }, [selectedDate]
    )

    async function checkUser()
    {
        if(state === null || state.currentUser.role !== 'USER')
        {
            navigate('/');
            return false;
        }

        return true;
    }

    async function getDeviceConsumptions()
    {
        return await axios.post(process.env.REACT_APP_SERVER_URL + '/get-device-consumptions', state.device)
        .then(
            response => {
                if(response.status === 200)
                {
                    return response.data
                }
            }
        )
        .catch(
            function(error)
            {
                console.log(error);
                return [];
            }
        )
    }

    async function filterEnergyConsumptionByDate()
    {
        filteredDeviceConsumptions = [];

        unfilteredDeviceConsumptions.forEach(
            function(deviceConsumption)
            {
                var date1 = new Date(deviceConsumption.date);
                var date2 = new Date(selectedDate);
                console.log('device date1 = ' + date1 + '\nselected date2 = ' + date2);
                if(date1.getDate() === date2.getDate())
                    filteredDeviceConsumptions.push(deviceConsumption);
            }
        )

        return filteredDeviceConsumptions;
    }

    return (state) ? (
        <div className="font-poppins">
            <div className="flex m-8 space-x-20">
                <div className="flex-none">
                    <button onClick={() => {navigate('/user', {state: state.currentUser});}}>User section - {state.currentUser.username}</button>
                </div>

                <div className="flex-grow" />

                <button className="logout-button" onClick={() => {navigate('/');}}>↦</button>

            </div>

            <div className="flex flex-row m-9 space-x-24">
                <div className="w-96 h-14">
                    <button className="selected-section" onClick={() => {setRefreshKey(refreshKey + 1);}}>OVERALL CONSUMPTION ON THE LAST 10 DAYS</button>
                </div>
            </div>
            <div className="flex flex-col items-center justify-center">
                <input type="date" onChange={(e) => {setSelectedDate(e.target.value);}}/>
                <Line data={chartData} />
            </div>
        </div>) : (<h></h>)
};

export default SelectedDevicePage;