import { create } from "zustand";

interface PaginationState {
  currentPage: { [pageId: string]: number };
  setCurrentPage: (pageId: string, currentPage: number) => void;
}

const usePaginationStore = create<PaginationState>(set => ({
  currentPage: {},
  setCurrentPage: (pageId, currentPage) =>
    set(state => ({
      currentPage: {
        ...state.currentPage,
        [pageId]: currentPage,
      },
    })),
}));

export default usePaginationStore;
