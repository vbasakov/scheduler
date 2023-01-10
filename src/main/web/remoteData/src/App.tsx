import { Scheduler } from "@aldabil/react-scheduler";
import {
    EventActions,
    ProcessedEvent,
    ViewEvent
} from "@aldabil/react-scheduler/types";
import { EVENTS, date } from "./events";

import axios, { AxiosInstance, AxiosTransformer } from 'axios';
// const axios = require('axios');


export default function App() {
    const fetchRemote = async (query: ViewEvent): Promise<ProcessedEvent[]> => {
        console.log({ query });
        /**Simulate fetchin remote data */
        return new Promise((res) => {
            // setTimeout(() => {
            //     res(EVENTS);
            // }, 3000);

            console.log(1234)
            // Делаем запрос пользователя с данным ID
            axios.get('/data.json', {
                params: {
                    ID: 12345
                }
            }).then(function (response) {
                // обработка успешного запроса
                console.log(response);
                res(response.data.map(
                    x => ({
                        event_id: x.event_id,
                        title: x.title,
                        start: date(x.start),
                        end: date(x.end),
                        disabled: x.disabled,
                        admin_id: x.admin_id
                    })))
            }).catch(function (error) {
                // обработка ошибки
                console.log(error);
            }).then(function () {
                // выполняется всегда
                console.log('finally');
            });

        });
    };

    const handleConfirm = async (
        event: ProcessedEvent,
        action: EventActions
    ): Promise<ProcessedEvent> => {
        console.log("handleConfirm =", action, event.title);
        if (action === "edit") {
            /** PUT event to remote DB */
        } else if (action === "create") {
            /**POST event to remote DB */
        }
        /**
         * Make sure to return 4 mandatory fields:
         * event_id: string|number
         * title: string
         * start: Date|string
         * end: Date|string
         * ....extra other fields depend on your custom fields/editor properties
         */
        // Simulate http request: return added/edited event
        return new Promise((res, rej) => {
            setTimeout(() => {
                res({
                    ...event,
                    event_id: event.event_id || Math.random()
                });
            }, 3000);
        });
    };

    const handleDelete = async (deletedId: string): Promise<string> => {
        // Simulate http request: return the deleted id
        return new Promise((res, rej) => {
            setTimeout(() => {
                res(deletedId);
            }, 3000);
        });
    };

    return (
        <Scheduler
            getRemoteEvents={fetchRemote}
            onConfirm={handleConfirm}
            onDelete={handleDelete}
        />
    );
}
