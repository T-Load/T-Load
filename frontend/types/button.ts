export const buttonSize: {
  [size: string]: string;
} = {
  xs: "h-[29px] text-[12px] font-medium rounded-[10px] px-[14px] py-[6px]",
  s: "h-[37px] text-[12px] font-medium rounded-[12px] px-[14px] py-[10px]",
  m: "h-[40px] text-[14px] font-medium rounded-[14px] px-[14px] py-[10px]",
  l: "h-[48px] text-[14px] font-bold rounded-[16px] px-[18px] py-[14px]",
  xl: "h-[52px] text-[16px] font-bold rounded-[16px] px-[18px] py-[14px]",
  "2xl": "h-[56px] text-[18px] font-bold rounded-[16px] px-[20px] py-[15px]",
  "3xl": "h-[72px] text-[22px] font-bold rounded-[20px] px-[24px] py-[20px]",
};

export type buttonSizeType = "xs" | "s" | "m" | "l" | "xl" | "2xl" | "3xl";

export const buttonColor: {
  [color: string]: string;
} = {
  primary: "bg-green-500 text-white hover:bg-green-700",
};

export type buttonColorType = "primary";
