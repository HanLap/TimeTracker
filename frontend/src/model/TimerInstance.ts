import moment, {Moment} from 'moment';

export interface TimerInstance {
  id?: number;
  name: string;
  startTime: Moment;
  endTime?: Moment;
}

export class TimerImpl implements TimerInstance {
  id?: number;
  name: string;
  startTime: Moment;
  endTime?: Moment;


  constructor(id: number | undefined, name: string, startTime: Moment, endTime: Moment | undefined) {
    this.id = id;
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  static parse(timer: TimerInstance): TimerImpl {
    return new TimerImpl(
      timer.id,
      timer.name,
      moment(timer.startTime),
      timer.endTime
        ? moment(timer.endTime)
        : undefined)
  }
}
